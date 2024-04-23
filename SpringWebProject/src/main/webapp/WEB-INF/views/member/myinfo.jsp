<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
	#profileImg
	{
     width:250px;
     height:250px;
     border:1px solid lightgray;
     border-radius: 50%;
 	}
</style>
</head>
<body>
	<!-- 이쪽에 메뉴바 포함 할꺼임 -->
     <jsp:include page="/WEB-INF/views/common/header.jsp"/>
     <script src="${contextPath}/resources/js/common.js"></script>

     <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>마이페이지</h2>
            <br>

            <div align="center">
                <img id="profileImg" src="${contextPath}<c:out value='${loginUser.profileUrl}' default='/resources/images/defaultProfile.png'/>" onclick="$('#profileImgFile').click();">
                <input type="file" id="profileImgFile" class="file" style="display:none;" accept="image/*">
            </div>
						
						<script>
							$(document).ready(function(){
								$('#profileImgFile').on('change', function(evt){
									
									if(this.files.length != 0){ // 현재 선택된 파일이 있을 경우
										let formData = new FormData();
									
										formData.append('uploadFile', this.files[0]);
										
										$.ajax({
											url: '${contextPath}/member/modifyProfile.do',
											type: 'post',
											data: formData,
											processData: false,
											contentType: false,
											success: function(result){
												if(result == 'SUCCESS'){ // 프로필 변경 성공 시 이미지 영역 바로 변경
													location.reload(); // 새로고침
												} else if(result == 'FAIL'){
													alertify.alert('프로필변경 서비스', '프로필 변경에 실패했습니다.');
												}
											},
											error: function(){
												console.log('프로필 이미지 변경용 ajax 통신 실패');
											}
										});
										
									} // if end
								})								
							});
						</script>
            
            <form action="${contextPath}/member/modify.do" method="post">
                <div class="form-group">
                    <label for="userId">* ID :</label>
                    <input type="text" class="form-control" id="userId" name="userId" value="${loginUser.userId}" readonly><br>
                    
                    <label for="userName">* Name :</label>
                    <input type="text" class="form-control" id="userName" name="userName" value="${loginUser.userName}"><br>
                    
                    <label for="email"> &nbsp; Email :</label>
                    <input type="email" class="form-control" id="email" name="email" value="${loginUser.email}"><br>
                    
                    <label for="phone"> &nbsp; Phone :</label>
                    <input type="tel" class="form-control" id="phone" name="phone" value="${loginUser.phone}"><br>
                    
                    <label for="address"> &nbsp; Address :</label>
                    <input type="text" class="form-control" id="address" name="address" value="${loginUser.address}"><br>
                    
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" name="gender" id="Male" value="M">
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" name="gender" id="Female" value="F">
                    <label for="Female">여자</label><br>
                    
                    <script>
                    	$(document).ready(function(){
                    		// loginUser.gender값과 일치하는 value값에 checked 속성 부여
                    		$('input[name=gender]').filter('[value=${loginUser.gender}]').attr('checked', true);
                    	});
                    </script>
                    
                </div>
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary">수정하기</button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
                </div>
            </form>

        </div>
        <br><br>
    </div>

    <!-- 회원탈퇴 버튼 클릭시 보여질 Modal -->
    <div class="modal" id="deleteForm">
        <div class="modal-dialog">
            <div class="modal-content">
            
                <!-- Modal Header -->
                <div class="modal-header">
                <h4 class="modal-title">회원탈퇴</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body" align="center">
                
                    <b>
			                        탈퇴 후 복구가 불가능합니다. <br>   
			                        정말로 탈퇴 하시겠습니까?
                    </b>

                    <form action="" method="post">
                        비밀번호 : 
                        <input type="password" name="" required>

                        <button type="submit" class="btn btn-danger">탈퇴하기</button>
                    </form>

                </div>
                
            </div>
        </div>
    </div>

    <!-- 이쪽에 푸터바 포함할꺼임 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>