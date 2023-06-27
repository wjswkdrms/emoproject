$(function(){
	let currentPage;
	let count;
	let rowCount;
	//후기 리스트 출력
	function reviewList(pageNum){
		currentPage = pageNum;
		$.ajax({
			url: 'productDetailReview.do',
			type: 'post',
			data: {pageNum:pageNum, product_num:$('#product_num').val()},
			dataType:'json',
			success: function(param){
				count = param.count;
				rowCount = param.rowCount;
				
				if(pageNum == 1){
					//처음 호출시는 해당 ID의 div
					//내부 내용물을 제거
					$('#output').empty();
				}
				
				$(param.list).each(function(index, item) {
					
					output1 = '<div class="rbox-left"><span>' + item.mem_id + '</span></div>';
					$('.review-box').prepend(output1);
					
					let output2 = '<h4>' + item.review_title + '</h4>';
					$('.rbox-right').prepend(output2);
					
					//output += '<div>'+'${product.productdetailVO.product_title}'+'</div>';
					let output3 = '<div>' + item.review_score + '</div>'
					output3 += '<span>' + item.review_content + '</span>';
					if (item.review_photo != null) {
						output3 += '<div class="rbpx-img">'
						output3 += '<img src="' + item.review_photo1 + '">';
						output3 += '</div>'
					}
					$('.rbox-right').append(output3);
				});
				
				//page button 처리				
				if(currentPage>=Math.ceil(count/rowCount)){
					//다음 페이지가 없음
					$('.paging-button').hide();
				}else{
					//다음 페이지가 존재
					$('.paging-button').show();
				}
			},
			error: function(){
				alert('네트워크 오류 발생');
			}
		});
	}
	
	//페이지 처리 이벤트 연결
	//(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$('.paging-button input').click(function() {
		reviewList(currentPage + 1);
	});
	
	reviewList(1);
});