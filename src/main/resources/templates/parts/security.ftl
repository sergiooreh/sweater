<#assign
known = Session.SPRING_SECURITY_CONTEXT??           <#--??- приводим к boolean. -->
>

<#if known>     <#--Если какой-то обэект определен в контексте - то работаем с сессией user-->
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal         <#--получить user-->
    name = user.getUsername()
    isAdmin = user.isAdmin()
        currentUserId = user.getId()
    >
<#else>             <#--для не авторизованных пользователей-->
    <#assign
    name = "unknown"
    isAdmin = false
    currentUserId = -1
    >
</#if>
