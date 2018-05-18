<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>蓝源Eloan-P2P平台</title>
		<#include "common/links-tpl.ftl" />
    <link type="text/css" rel="stylesheet" href="/css/account.css"/>
    <link rel="stylesheet" href="/js/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
    <script type="text/javascript" src="/js/plugins-override.js"></script>
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script src="/js/plugins/datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript">
        $(function () {

            //开启日期插件
            $(".beginDate,.endDate").click(function () {
                WdatePicker({
                    dateFmt: "yyyy-MM-dd"
                });
            });
            //回显当前高级查询状态栏
            $("select option[value=${qo.state}]").prop("selected", "true");

            $("#pagination").twbsPagination({
                totalPages:${pageResult.totalPage},
                visiblePages: 5,
                startPage:${qo.currentPage},
                onPageClick: function (e, page) {
                    $("#currentPage").val(page);
                    $("[name=searchForm]").submit();
                }
            });
        });
    </script>
</head>
<body>

<!-- 网页顶部导航 -->
		<#include "common/head-tpl.ftl" />
		
		<#assign currentNav="personal" />
<!-- 网页导航 -->
		<#include "common/navbar-tpl.ftl" />

<div class="container">
    <div class="row">
        <!--导航菜单-->
        <div class="col-sm-3">
					<#assign currentMenu="iplog" />
					<#include "common/leftmenu-tpl.ftl" />
        </div>
        <!-- 功能页面 -->
        <div class="col-sm-9">
            <form action="/iplog.do" name="searchForm" class="form-inline" method="post">
                <input type="hidden" id="currentPage" name="currentPage" value="1"/>
                <div class="form-group">
                    <label>时间范围</label>
                    <input type="text" class="form-control beginDate" name="beginTime"
                           value='${(qo.beginTime?string("yyyy-MM-dd"))!""}'/>
                </div>
                <div class="form-group">
                    <label></label>
                    <input type="text" class="form-control endDate" name="endTime"
                           value='${(qo.endTime?string("yyyy-MM-dd"))!""}'/>
                </div>
                <div class="form-group">
                    <label>登录状态</label>
                    <select class="form-control" name="state">
                        <option value="-1">全部</option>
                        <option value="0">登录失败</option>
                        <option value="1">登录成功</option>
                    </select>
                </div>
                <div class="form-group">
                    <button id="search" type="submit" class="btn btn-success"><#--<i class="icon-search">a</i>-->
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查询
                    </button>
                </div>
            </form>
            <div class="panel panel-default" style="margin-top: 20px;">
                <div class="panel-heading">
                    登录日志
                </div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>用户</th>
                        <th>登录时间</th>
                        <th>登录ip</th>
                        <th>登录状态</th>
                    </tr>
                    </thead>
                    <tbody>
								<#list pageResult.data as data>
                                <tr>
                                    <td>${data.username}</a></td>
                                    <td>${data.loginTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    <td>${data.ip}</td>
                                    <td>${data.stateByView}</td>
                                    <#if loginInfo.userType == 1>
                                    <td>${data.userTypeOfVeiw}</td></#if>
                                </tr>
                                </#list>
                    </tbody>
                </table>
                <div style="text-align: center;">
                    <ul id="pagination" class="pagination-sm"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "common/footer-tpl.ftl" />
</body>
</html>