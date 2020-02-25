<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
<@l.logout/>
    </div>
    <form method="post">
        <input type="text" name="text" placeholder="Enter Value">
        <input type="text" name="tag" placeholder="Enter tag">
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
            <br/>
        </div>
        <#else>
        No messages
    </#list>
</@c.page>