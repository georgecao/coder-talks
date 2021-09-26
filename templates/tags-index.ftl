<#include "header.ftl">

<#include "menu.ftl">

<div id="main">
    <ul class="posts">
        <header>
            <h1>标签云</h1>
        </header>
        <#list tags as tag>
            <button type="button" class="btn btn-secondary">
                <a href="${content.rootpath}${tag.uri}">${tag.name}</a>
                <span class="badge badge-light">${tag.tagged_posts?size}</span>
            </button>
        </#list>
    </ul>
</div>

<#include "footer.ftl">