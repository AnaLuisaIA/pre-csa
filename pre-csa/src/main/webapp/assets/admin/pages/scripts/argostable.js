var ArgosTable = new function(){
	return {
		init: function(options){
			var unsortableIndex = [];
			$('th.unsortable').each(function(){
				unsortableIndex.push($(this).index());
				
			});
						
			var table = $('#datatable').DataTable( {
		        "processing": true,
		        "serverSide": true,
		        "searchDelay": 950,        
			        "ajax": {
			        "url": options.url
			        
			     },
		        "columns": options.columns
		    } );
		}
	}
}