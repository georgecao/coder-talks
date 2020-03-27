<#include "header.ftl">
	
	<#include "menu.ftl">

	<#list published_posts as post>
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
  	</#list>

	<hr />
	
	<p><a href="${content.rootpath}${config.archive_file}">点击查看更多文章</a>.</p>

<#include "footer.ftl">