<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<h2>Registration</h2>
    <#if message??>
        ${message}
    </#if>
<@l.login "/registration" />
</@c.page>