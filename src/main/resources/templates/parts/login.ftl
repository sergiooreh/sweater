<#macro login       path >
    <form action="${path}" method="post">       <#--path меняеться-->
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <br/>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <br/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="Sign In"/></div>
    </form>
</#macro>

<#macro logout>                                     <#--еще один macro-->
    <form action="/logout" method="post">
        <input type="submit" value="Sign out">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>