<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
          name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <link href="../static/css/styles.css" rel="stylesheet">
    <title>Document</title>
</head>
<body>
<div class="container">
    <div class="generator">
        <table>
            <tr>
                <th>Сотрудник</th>
                <th>Понедельник</th>
                <th>Вторник</th>
                <th>Среда</th>
                <th>Четверг</th>
                <th>Пятница</th>
                <th>Суббота</th>
                <th>Воскресенье</th>
            </tr>
            <#if items??>
                <#list items as item>
                    <tr>
                        <td>${item.firstName} ${item.lastName}</td>
                        <td>${item.shift}</td>
                        <td>${item.shift}</td>
                        <td>${item.shift}</td>
                        <td>${item.shift}</td>
                        <td>${item.shift}</td>
                        <td>${item.shift}</td>
                        <td>${item.shift}</td>
                    </tr>
                </#list>
            </#if>
        </table>
        <a href="/generate" class="button">Генерировать</a>
        <a href="/addEmployees">Добавить сотрудника</a>
    </div>

    <#if shifts??>
        <#list shifts as shift>

            <p> ${shift.name}
               <#list shift.employees as a>
                   <p>${a.lastName}</p>
                </#list>

        </#list>
    </#if>
</div>
</body>
</html>