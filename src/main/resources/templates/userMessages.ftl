<#import "parts/common.ftl" as c>
<@c.page>
    <#if message??>
    <#include "parts/messageEdit.ftl"/>
    </#if>

    <#include  "parts/messageList.ftl"/>
</@c.page>
