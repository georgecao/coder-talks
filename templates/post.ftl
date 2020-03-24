<#include "header.ftl">
	
	<#include "menu.ftl">

	<#if (content.title)??>
		<div class="page-header">
			<h1><#escape x as x?xml>${content.title}</#escape></h1>
		</div>
	<#else>
	</#if>

	<p><em>${content.date?string("yyyy年MM月dd日")}</em>
		<#if (content.tags)??>
			<#list content.tags as tag>
				<span class="badge badge-dark">${tag}</span>
			</#list>
		</#if>
	</p>
	<p>${content.body}</p>

	<hr />
	
<#include "footer.ftl">