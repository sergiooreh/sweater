<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
<@l.logout/>
        <span><a href="/user">User List</a> </span>
    </div>
    <form method="post" enctype="multipart/form-data">      <#--Определяет способ кодирования данных формы при их отправке на сервер. Обычно устанавливать значение атрибута enctype не требуется, данные вполне правильно понимаются на стороне сервера.
                                                            Однако если используется поле для отправки файла (input type="file"), следует определить атрибут enctype как multipart/form-data.-->
        <input type="text" name="text" placeholder="Enter Value">
        <input type="text" name="tag" placeholder="Enter tag">
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Add</button>
    </form>
    <br/>
    <h3>Find something:</h3>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Find</button>
    </form>
</div>
<div><h3>Список сообщений:</h3></div>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <strong>${message.tag}</strong>
            <i>${message.authorName}</i>
            <div>
                <#if message.filename??>
                    <img src="/img/${message.filename}">
                </#if>
            </div>
            <br/>
        </div>
        <#else>
        No messages
    </#list>
</@c.page>