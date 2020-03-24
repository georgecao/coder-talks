<#include "header.ftl">

	<#include "menu.ftl">
	
	<div class="page-header">
		<h1>标签: ${tag}</h1>
	</div>
	
	<!--<ul>-->
		<#list tag_posts as post>
		<#if (last_month)??>
			<#if post.date?string("yyyy年MM月") != last_month>
				</ul>
				<h4>${post.date?string("yyyy年MM月")}</h4>
				<ul>
			</#if>
		<#else>
			<h4>${post.date?string("yyyy年MM月")}</h4>
			<ul>
		</#if>
		
		<li>${post.date?string("dd")} - <a href="${content.rootpath}${post.uri}">${post.title}</a></li>
		<#assign last_month = post.date?string("yyyy年MM月")>
		</#list>
	</ul>
	
<#include "footer.ftl">