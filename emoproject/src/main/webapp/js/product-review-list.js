$(function(){
	let currentPage;
	let count;
	let rowCount;
	let i = 0;
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
					let score_star = ((item.review_score/100)*5).toFixed(2);
					let score_avg = (item.review_score / count).toFixed(2);
					
					output1 = '<div class="review-box">';
					output1 += '<div class="rbox-left"><div class="re-memid">' + item.mem_id + '</div></div>';
					output1 += '<div class="rbox-right">';
					output1 += '<h4 class="re-ti">' + item.review_title + '</h4>';
					output1 += '<div class="re-score">' 
					
					output1 += score_star+'('+item.review_score + ')</div>'
					output1 += '<div class="re-content">' + item.review_content + '</div>';
					if (item.review_photo != null) {
						output1 += '<div class="rbpx-img">'
						output1 += '<img src="' + item.review_photo1 + '">';
						output1 += '</div>'
					}
					//output1 += '<div class="re-product-ti"> </div>';
					output1 += '</div>';
					output1 += '</div>';
					$('#output').append(output1);
					
					
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