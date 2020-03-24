<#include "header.ftl">
	
	<#include "menu.ftl">

	<#list posts as post>
  		<#if (post.status == "published")>
  			<a href="${post.uri}"><h1><#escape x as x?xml>${post.title}</#escape></h1></a>
  			<p>
				${post.date?string("yyyy年MM月dd日")}
				<#if (post.tags)??>
					<#list post.tags as tag>
						<span class="badge badge-dark">${tag}</span>
					</#list>
				</#if>
			</p>
  			<p>${post.body}</p>
  		</#if>
  	</#list>

	<hr />
	
	<p><a href="${content.rootpath}${config.archive_file}">点击查看更多文章</a>.</p>

<#include "footer.ftl">