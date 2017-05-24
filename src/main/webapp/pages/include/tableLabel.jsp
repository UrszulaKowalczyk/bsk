<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/editTableLabel.js"/>"></script>

<c:if test="${tableLabelTableInfo.visible}">

<div class="container">
	<h2>${tableLabelTableInfo.name}   ( Label: ${tableLabelTableInfo.level} )</h2>

	<table class="table">
		<thead>
			<tr>
				<c:forEach items="${tableLabelTableInfo.columnsNames}" var="columnName">
					<th>${columnName}</th>
				</c:forEach>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tableLabelList}" var="tableLabel">

				<tr id="tableLabel_${tableLabel.id}">


					<td>${tableLabel.id}</td>
					<td id="tableName_${tableLabel.id}">${tableLabel.tableName}</td>
					<td id="label_${tableLabel.id}">${tableLabel.label}</td>
					<c:if test="${tableLabelTableInfo.canWrite}">
						<td>
							<button type="button" id="edit_tableLabel_${tableLabel.id}"
								onclick="editTableLabel(${tableLabel.id})">Edit</button>
							<button type="button" id="save_tableLabel_${tableLabel.id}"
								style="display: none;"
								onclick="updateTableLabel('<c:url value="/updateTableLabel" />', ${tableLabel.id});">
								Save</button>
						</td>
						<td>
							<button type="button" id="delete_tableLabel_${tableLabel.id}"
							onclick="deleteRow('<c:url value="/deleteTableLabel" />', ${tableLabel.id});" > Delete </button>
						</td>
					</c:if>


				</tr>

			</c:forEach>
			<form name="myFormTableLabel" action="<c:url value="/addTableLabel" />"
				method="post">
				<c:if test="${tableLabelTableInfo.canWrite}">
					<tr>
						<td><input type="text" /></td>
						<td><input name="tableName" type="text" /></td>
						<td><input name="label" type="text" /></td>
						<td><input type="submit" value="Save" /></td>
					</tr>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</c:if>
			</form>
		</tbody>
	</table>


</div>

</c:if>