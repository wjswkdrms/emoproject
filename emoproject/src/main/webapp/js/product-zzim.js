$(function(){
	//찜 선택여부
	function selectZzim(){
		$.ajax({
			url:'getZzim.do',
			type: 'post',
			data:{product_num:$('#output_zzim').attr('data-num')},
			dataType: 'json',
			success:function(param){
				displayZzim(param);
			},
			error:function(){
				alert('네트위크 오류 발생');
			}
		});
	}
	
	//찜 등록(삭제)이벤트 처리
	$('#output_zzim').click(function(){
		$.ajax({
			url: 'writeZzim.do',
			type: 'post',
			data:{product_num:$(this).attr('data-num')},
			dataType: 'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 좋아요를 눌러주세요');
				}else if(param.result == 'success'){
					displayZzim(param);
				}else{
					alert('좋아요 표시 오류 발생');
				}
			},
			error:function(){
				alert('네트위크 오류 발생');
			}
		});
	});
	
	//찜 표시
	function displayZzim(param){
		let output;
		if(param.z_status == 'noZzim'){
			output = '../images/zzim_01.png';
		}else{
			output = '../images/zzim_02.png';
		}
		// 문서 객체 설정
		$('#output_zzim').attr('src',output);
		//$('#output_zcount').text(param.zzim-count);
	}
	
	//초기 데이터 호출
	selectZzim();
	
});