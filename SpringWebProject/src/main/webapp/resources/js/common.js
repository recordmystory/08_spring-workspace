$(document).ready(function(){
	let totalSize = 0;
	$('.file').change(function(evt){
		
		const files = evt.target.files;
		
		for(let i=0; i<files.length; i++){
			if(files[i].size > 10 * 1024 * 1024){ 
				alert("첨부파일의 최대 크기는 10MB까지입니다.");
				evt.target.value = '';
				return;
			}
			totalSize += files[i].size;
			if(totalSize > 100 * 1024 * 1024){ 
				alert("전체 첨부파일의 최대 크기는 100MB까지입니다.");
				evt.target.value = '';
				return;
			}
		}
	});
});