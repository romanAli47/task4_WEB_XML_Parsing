<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 16.11.2017
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<meta http-equiv="refresh" content="30">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><link rel='stylesheet' type="text/css" href="<c:url value="/css/bootstrap.min.css"/>"/>

   
</head>
<body>
<br/>
<br/>
<div>
    <a href="<c:url value="/jsp/app.jsp"/>" >Назад</a>
    <c:if test="${!empty bankDeposite}">
        <div class="container">
            <div  class="row">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
                    <h1 align="center">${parserValue}</h1>
                    <table class="table table-bordered">
                        <tr class="bg-info row">
                            <th class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">bankname</th>
                            <th class="col-xs-3 col-sm-3 col-md-3  col-lg-3 ">account-id</th>
                            <th class="col-xs-3 col-sm-3 col-md-3  col-lg-3 ">country</th>
                            <th class="col-xs-3 col-sm-3 col-md-3  col-lg-3 ">depositor</th>
                            <th class="col-xs-3 col-sm-3 col-md-3  col-lg-3 ">amount</th>
                            <th class="col-xs-3 col-sm-3 col-md-3  col-lg-3 ">profitability</th>
                            <th class="col-xs-3 col-sm-3 col-md-3  col-lg-3 ">timeConstraints</th>
                        </tr>
                        <c:forEach items="${bankDeposite}" var="bankDepositeList">
                            <tr class="row">
                                <td class="col-xs-3 col-sm-3 col-md-3  col-lg-3" >${bankDepositeList.bankName}</td>
                                <td class="col-xs-3 col-sm-3 col-md-3  col-lg-3" >${bankDepositeList.accountId}</td>
                                <td class="col-xs-3 col-sm-3 col-md-3  col-lg-3" >${bankDepositeList.country}</td>
                                <td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${bankDepositeList.depositor}</td>
                                <td class="col-xs-3 col-sm-3 col-md-3  col-lg-3" >${bankDepositeList.amount}</td>
                                <td class="col-xs-3 col-sm-3 col-md-3  col-lg-3" >${bankDepositeList.profitability}</td>
                                <td class="col-xs-3 col-sm-3 col-md-3  col-lg-3" >${bankDepositeList.timeConstraints}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </c:if>
</div>
</body>
<style>
    a {
        color:black;
        margin-left:300px;
    }
</style>
</html>