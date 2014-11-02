<div class="cookies-directive ${cssBottom}">
    <p>
        <span class="cookies-directive-body">
            <g:if test="${raw(body)}">
                ${raw(body)}
            </g:if>
            <g:else>
                <g:message code="cookies.directive.default.message" />
            </g:else>
        </span>&nbsp;
        <a href="#" class="cookies-directive-button accept"><g:message code="cookies.directive.accept.label" /></a>
        <g:if test="${displayDecline}">
            &nbsp;<a href="#" class="cookies-directive-button decline"><g:message code="cookies.directive.decline.label" /></a>
        </g:if>
    </p>
</div>

<g:javascript>
    jQuery('body').cookiesDirective({
        expires: ${expires},
        domain: "${domain}",
        path: "${path}",
        secure: ${secure}
    });
</g:javascript>
