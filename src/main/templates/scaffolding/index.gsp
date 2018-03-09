<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="\${message(code: '${propertyName}.label', default: '${className}')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <asset:stylesheet src="grid.css"/>
        <asset:javascript src="grid.js"/>
    </head>
    <body>
        <ul class="nav nav-pills">
            <li><a class="home" href="\${createLink(uri: '/')}">
                <span class="glyphicon glyphicon-home"></span> <g:message code="default.home.label"/>
            </a></li>
            <li><g:link class="create" action="create">
                <span class="glyphicon glyphicon-plus"></span> <g:message code="default.new.label" args="[entityName]" />
            </g:link></li>
        </ul>

        <div id="list-${propertyName}" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="\${flash.message}">
                <div class="message" role="status">\${flash.message}</div>
            </g:if>
            <f:table collection="\${${propertyName}List}" />

            <div class="pagination">
                <g:paginate total="\${${propertyName}Count ?: 0}" />
            </div>
        </div>

    </body>
</html>