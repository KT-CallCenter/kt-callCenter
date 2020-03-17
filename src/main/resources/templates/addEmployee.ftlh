<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>add</title>
</head>
<body>
<form action="/addEmployees/form" method="post">
    <input type="text" name="firstName" placeholder="Введите имя">
    <input type="text" name="lastName" placeholder="Введите фамилию">

<#--    <#if items??>-->

<#--        <#list items as item>-->

<#--            <select name="shift">-->
<#--                <#list item as shift>-->
<#--                <option>${item.shift}</option>-->
<#--                <option>${item.shift}</option>-->
<#--                </#list>-->
<#--            </select>-->

<#--            <select name="language">-->
<#--                <option>${item.language}</option>-->
<#--            </select>-->

<#--            <select name="region">-->
<#--                <option>${item.region}</option>-->
<#--            </select>-->


<#--        </#list>-->
<#--    </#if>-->

<button type="submit"> Добавить </button>
</form>
<div>
    <#if errors?? >
        <p>Входные данные с ошибками!</p>
        <p>Заполните все поля!</p>
        <#list errors as fieldError>
            <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
        </#list>
    </#if>
</div>
<div>
    <#if addEmployee?? >
        <p>${addEmployee}</p>
    </#if>
</div>
<div>
    <#if errorsExist?? >
        <p>${errorsExist}</p>
    </#if>
</div>

</body>
</html>