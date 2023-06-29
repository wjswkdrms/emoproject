$(function() {
	let currentPage;
	let count;
	let rowCount;
	let i = 0;

	//후기 리스트 출력
	function reviewList(pageNum) {
		currentPage = pageNum;
		$.ajax({
			url: 'productDetailReview.do',
			type: 'post',
			data: { pageNum: pageNum, product_num: $('#product_num').val() },
			dataType: 'json',
			success: function(param) {
				count = param.count;
				rowCount = param.rowCount;

				if (pageNum == 1) {
					//처음 호출시는 해당 ID의 div
					//내부 내용물을 제거
					$('#output').empty();
				}

				$(param.list).each(function(index, item) {
					let score_star = ((item.review_score / 10) * 5).toFixed(2);


					output1 = '<div class="review-box">';
					output1 += '<div class="rbox-left"><div class="re-memid">' + item.mem_id + '</div></div>';
					output1 += '<div class="rbox-right">';
					output1 += '<div class="re-product-ti">[ ' + item.product_title + ' ]</div>';
					output1 += '<h4 class="re-ti">' + item.review_title + '</h4>';
					output1 += '<div class="re-score">';
					output1 += '<img id="sco-' + i + '-img" class="sco-img" src="../images/star0">';
					output1 += '<span class="sco-te">' + score_star + '(' + item.review_score + '/10)</span></div>';
					output1 += '<div class="re-content">' + item.review_content + '</div>';
					console.log('img.review_photo1');
					if (item.review_photo1 != null) {
						output1 += '<div class="rbox-img">';
						output1 += '<img class="imgmodal" src="../upload/' + item.review_photo1 + '" width="100" style="cursor: pointer">';
						output1 += '</div>';
					}
					output1 += '</div>';
					output1 += '</div>';
					$('#output').append(output1);

					let img = score_img(score_star);
					$('#sco-' + i + '-img').attr('src', img);
					i++;
				});

				//page button 처리				
				if (currentPage >= Math.ceil(count / rowCount)) {
					//다음 페이지가 없음
					$('.paging-button').hide();
				} else {
					//다음 페이지가 존재
					$('.paging-button').show();
				}


			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});
	}

	//페이지 처리 이벤트 연결
	//(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$('.paging-button input').click(function() {
		reviewList(currentPage + 1);
	});

	function avg_score_img() {

		let score_sum = 0;
		let score_avg = 0;

		let score_sum_re = 0;
		let score_avg_re = 0;
		$.ajax({
			url: 'productReviewScore.do',
			type: 'post',
			data: { product_num: $('#product_num').val() },
			dataType: 'json',
			success: function(param) {
				count = param.count;
				$(param.list).each(function(index, item) {
					score_sum += ((item.review_score / 10) * 5);
					score_sum_re += item.review_score
				});
				score_avg = (score_sum / count).toFixed(2);
				score_avg_re = (score_sum_re / count).toFixed(2);

				let out

				if (count > 0) {
					out = '<div class="re-avg-text">평균 별점: ' + score_avg + '(' + score_avg_re + '/10)' + '</div>';
				} else {
					out = '<div class="re-avg-text">평균별점: 0.0(0/10)</dvi>';
				}
				$('.star-img').append(out);

				let img = score_img(score_avg);
				$('#star-output').attr('src', img);
			},
			error: function() { }
		});
	}

	function score_img(score_avg) {
		let img;
		if (score_avg >= 0.5 && score_avg < 1) {
			img = '../images/star_05.png';
		} else if (score_avg >= 1 && score_avg < 1.5) {
			img = '../images/star_10.png';
		} else if (score_avg >= 1.5 && score_avg < 2) {
			img = '../images/star_15.png';
		} else if (score_avg >= 2 && score_avg < 2.5) {
			img = '../images/star_20.png';
		} else if (score_avg >= 2.5 && score_avg < 3) {
			img = '../images/star_25.png';
		} else if (score_avg >= 3 && score_avg < 3.5) {
			img = '../images/star_30.png';
		} else if (score_avg >= 3.5 && score_avg < 4) {
			img = '../images/star_35.png';
		} else if (score_avg >= 4 && score_avg < 4.5) {
			img = '../images/star_40.png';
		} else if (score_avg >= 4.5 && score_avg < 5) {
			img = '../images/star_45.png';
		} else if (score_avg == 5) {
			img = '../images/star_5.png';
		} else {
			img = '../images/star_0.png'
		}
		return img;
	}

	//이미지 클릭시 해당 이미지 모달
	//.imgmodal 는 동적으로 생성되었기 때문에 $('.imgmodal').click 으로 동작 못함
	$(document).on("click", ".imgmodal", function(){
		let img = new Image();
		img.src = $(this).attr("src")
		$('.modalBox').html(img);
		$(".modal").show();
	});
	
	// 모달 클릭할때 이미지 닫음
	$(".modal").click(function(e) {
		$(".modal").toggle();
	});



	reviewList(1);
	avg_score_img();
});