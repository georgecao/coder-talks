<#include "header.ftl">
<#include "menu.ftl">

<#if (content.title)??>
    <div class="page-header">
        <h1><#escape x as x?xml>${content.title}</#escape></h1>
    </div>
<#else>
</#if>

<p>
    <a href="/about.html"><strong>${content.author!config.site_author}</strong></a>于${content.date?string("yyyy年MM月dd日")}
    <#if (content.tags)??>
        <#list content.tags as tag>
            <span class="badge badge-dark">${tag}</span>
        </#list>
    </#if>
</p>
<p>${content.body}</p>

<hr/>
<div>
    <span class="left">
    <#if (content.previousContent.uri)??>
        <a href="${config.site_host}/${content.previousContent.uri}" title="${content.title}">上一篇</a>
    </#if>
	</span>
    <span class="right">
    <#if (content.nextContent.uri)??>
        <a href="${config.site_host}/${content.nextContent.uri}" title="${content.title}">下一篇</a>
    </#if>
	</span>
</div>
<#include "disqus.ftl">
<#include "footer.ftl">