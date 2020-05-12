<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
setTimeout(function() {
    $('.alert').fadeOut('fast');
}, 5000);

</script>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
			<c:choose>
				<c:when test="${ not empty errmsg }">
					<div class="alert alert-danger">${errmsg}</div>
				</c:when>
				<c:when test="${ not empty infomsg }">
					<div class="alert alert-info">${infomsg}</div>
				</c:when>
				<c:when test="${ not empty succmsg }">
					<div class="alert alert-success">${succmsg}</div>
				</c:when>
			</c:choose>
		<div class="alert alert-warning" id="warnmsg" style="display:none;"></div>
	</div>
</div>


