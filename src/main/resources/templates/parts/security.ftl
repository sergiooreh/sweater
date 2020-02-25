<#assign
known = Session.SPRING_SECURITY_CONTEXT??           <#--??- приводим к boolean. -->
>

<#if known>     <#--Если какой-то обэект определен в контексте - то работаем с сессией user-->
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal         <#--получить user-->
    name = user.getUsername()
    isAdmin = user.isAdmin()
    >
<#else>             <#--если нет-->
    <#assign
    name = "unknown"
    isAdmin = false
    >
</#if>