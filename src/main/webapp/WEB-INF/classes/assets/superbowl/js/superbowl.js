/** ------------------------------------
    SuperBOWL JavaScript functions
    ------------------------------------ */

/**
 * ------------------------------------ DataTables and its extensions are
 * extremely configurable libraries and almost every aspect of the enhancements
 * they make to HTML tables can be customised. Features can be enabled, disabled
 * or customised to meet your exact needs for your table implementations.
 * Customisation of these options are performed by defining options in the
 * $().DataTable() constructor. ------------------------------------
 */
$(function() {

	"use strict";
	
	$(document).ready(function() {
		
		$("#superbowlDataTable").DataTable({
		    language: {
				"url": "//cdn.datatables.net/plug-ins/1.10.13/i18n/German.json"
			},
			fixedHeader: {
        		header: true,
        		footer: false
    		},
			select: true,
			dom: 'lBfrtip<"clear">',
			buttons: [
		       	{
		           extend: 'print',
		           customize: function ( win ) {
		              $(win.document.body)
		                    .css( 'font-size', '10pt' )
		                       .prepend(
		                           '<img src="http://datatables.net/media/images/logo-fade.png" style="position:absolute; top:0; left:0;" />'
		                       );
		 		                    $(win.document.body).find( 'table' )
		                       .addClass( 'compact' )
		                       .css( 'font-size', 'inherit' );
		               }
	            },
	            /**
				 * { extend: 'copyHtml5', exportOptions: { columns:
				 * ':contains("Office")' } },
				 */
				'pdfHtml5',
				'excelHtml5',
				'csvHtml5'
			]
		});	
		
		$("#scrollyDataTable").DataTable({
			language: {
				"url": "//cdn.datatables.net/plug-ins/1.10.13/i18n/German.json"
			},
			fixedHeader: {
        		header: true,
        		footer: false
    		},
			select: {
				select: true,
	            style: 'single'
	        },
	        scrollY: 450,
			dom: 'lBfrtip<"clear">',
			buttons: [
	        	{
		            extend: 'print',
	                customize: function ( win ) {
	                    $(win.document.body)
	                        .css( 'font-size', '10pt' )
	                        .prepend(
	                            '<img src="http://datatables.net/media/images/logo-fade.png" style="position:absolute; top:0; left:0;" />'
	                        );
	 
	                    $(win.document.body).find( 'table' )
	                        .addClass( 'compact' )
	                        .css( 'font-size', 'inherit' );
	                }
                },
                /**
				 * { extend: 'copyHtml5', exportOptions: { columns:
				 * ':contains("Office")' } },
				 */
				'pdfHtml5',
				'excelHtml5',
				'csvHtml5'
			]
		});
		
		$("#defaultDataTable").DataTable({
			language: {
				"url": "//cdn.datatables.net/plug-ins/1.10.13/i18n/German.json"
			},
			fixedHeader: {
        		header: true,
        		footer: false
    		},
			select: {
				select: true,
	            style: 'single'
	        },
	        scrollY: 450,
			dom: 'lBfrtip<"clear">'
		});
		
		$(".datatables").DataTable();

		/**
		 * JSONP
		 */
	    $('#example').DataTable({
	        "processing": true,
	        "serverSide": true,
	        "ajax": {
	            "url": "scripts/jsonp.php",
	            "dataType": "jsonp"
	        }
	    });
		
		$("#example1").DataTable();
		
		$('#example2').DataTable({
				"bPaginate": true,
				"bLengthChange": false,
				"bFilter": false,
				"bSort": true,
				"bInfo": true,
				"bAutoWidth": false
		});
		
	});

});
