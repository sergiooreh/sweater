<#import "parts/common.ftl" as c>

<@c.page>
    <h2><span class="badge badge-dark">User editor</span></h2>
    <br>
    <form action="/user" method="post">
        <input class="form-control" type="text"name="username" value="${user.username}" />
        <br>
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId"/>
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <button type="submit" class="btn btn-dark">Save</button>
    </form>
</@c.page>
