<#import "parts/common.ftl" as c>
<@c.page>
    <h3>${userChannel.username}</h3>
    <#if !isCurrentUser>
        <#if isSubscriber>
            <a class="btn btn-info" href="/user/unsubscribe/${userChannel.id}">Unsubscribe</a>
        <#else>
            <a class="btn btn-info" href="/user/subscribe/${userChannel.id}">Subscribe</a>
        </#if>
    </#if>
    <div class="container my-3">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        Subscriptions
                        <h3 class="card text"><a href="/user/subscriptions/${userChannel.id}/list">${subscriptionsCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card">
                        <div class="card-body">
                            Subscribers
                            <h3 class="card text">
                                <a href="/user/subscribers/${userChannel.id}/list">${subscribersCount}</a>
                            </h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#if message??>
        <#include "parts/messageEdit.ftl"/>
    </#if>

    <#include  "parts/messageList.ftl"/>
</@c.page>
