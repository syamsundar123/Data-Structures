

Ext.application({
    name : 'Fiddle',
    launch : function() {
	var start = -5;
	var count = 0;
	var languageStore = Ext.create('Ext.data.Store',{
		
		
	})
		var form = Ext.create('Ext.form.Panel',{
			id:'movieForm',
			renderTo:Ext.getBody(),
			border:true,
			title:'Movie Advance search',
			autoEl:'middle',			
			items:[
				
								{
					xtype:'textfield',
					fieldLabel:'Movie Name',
					margin:'40 0 0 200'
					
					
				},
				{
					xtype:'textfield',
					fieldLabel:'Director Name',
					margin:'-30 0 0 900'
					
					
				},
				{
					xtype:'datefield',
					fieldLabel:'Release Year',
					margin:'80 0 10 200'
				
				},
				{
					xtype:'combobox',
					fieldLabel:'Language',
					margin:'-40 0 50 900',
					store:{
						extend:'Ext.data.Store',
						
						fields:['language'],
						data:[
							{
								"language":'Telugu'
							},
							{
								"language":'Hindi'
							},
							{
								language:"English"
							}
						]
					},
					
					displayField:'language',
	
					
				}
				
				],
			
			
			});
			
		var buttonBox = Ext.create('Ext.Container',{
			
			
			width:"100%",
			border:'true',
			margin:'50 0 0 0',
			defaults:{
				xtype:'button',
				margin:5
			},
			items:[
				{
					text:'Search',
					id:'searchButton',
					margin:'5 5 5 700',
					
				},
				{
					text:'Reset',
					handler:function()
					{
						Ext.getCmp('searchButton').setDisabled(false);
						Ext.getCmp('detailsGrid').hidden = false;
					}
				}
			],
			renderTo:Ext.getBody()
		});
		
		
						
						//Ext.getCmp('searchButton').setDisabled(true),
						Ext.define('movies', {
							     extend: 'Ext.data.Model',
							     fields: [
							         {name: 'Title', type: 'string'},
							         {name: 'description',  type: 'string'},
							         {name: 'releaseYear',       type: 'int'},
							         {name: 'language',  type: 'int'},
  									 {name: 'directorName',  type:'string'},
							         {name: 'rating',       type: 'string'},
							         {name: 'specialFeatures',  type: 'string'},
									{name:'filmId',type:'int'}
							     ],
								proxy:{
									type:'rest',
									url:'http://localhost:8080/Summer_Internship_Backend/dummy.do',
									
								}
									
							 });
				
				var m = Ext.create('Ext.data.Store',{
							storeId:'Movies',
							pageSize:10,
							autoLoad:true,
							model:'movies',
							
							proxy:{
								type:'ajax',
								//enablePaging:true,
								
								url:'http://localhost:8080/Summer_Internship_Backend/dummy.do',
								
								reader: {
									type: 'json',
									rootProperty:'Items',
									totalProperty:'total'
								        
								    }
							},
							
							
							
						}).load({
							params:{
								start:0,
								limit:10
							}
						});
					Ext.create('Ext.grid.Panel',{
							
							title:'Movie Grid',
							store:Ext.data.StoreManager.lookup('Movies'),
							id:'detailsGrid',
							
							width:"100%",
							columns:[
								{
									text:'Title',
									dataIndex:'title',
									width:200
									
								},
								{
									text:'Description',
									dataIndex:'description',
									width:200
								},
								{
									text:'Release Year',
									dataIndex:'releaseYear',
									width:200
								},{
									text:'Language',
									dataIndex:'language',
									width:200
								},
								{
									text:'Director',
									dataIndex:'directorName',
									width:310
								},{
									text:'Rating',
									dataIndex:'rating',
									width:200
								},
								{
									text:'Special Features',
									dataIndex:'specialFeatures',
									width:200
								}
							],
							renderTo:Ext.getBody(),
							selModel:{
								type:'checkboxmodel',
								checkOnly:true,
								model:'SIMPLE',
								injectCheckbox:'first',
							listeners:{
								'select':function()
								{
									count += 1;
									if(count > 0)
									{
										Ext.getCmp('deleteButton').setDisabled(false);
									}
									if(count  == 1)
									{
										Ext.getCmp('editButton').setDisabled(false);
									}
									else
									{
										Ext.getCmp('editButton').setDisabled(true);
									}
									
								},
								'deselect':function()
								{
									count -= 1;
									if(count <= 0)
									{
										Ext.getCmp('deleteButton').setDisabled(true);
									}
									if(count != 1)
									{
										Ext.getCmp('editButton').setDisabled(true);
									}
									else
									{
										Ext.getCmp('editButton').setDisabled(false);
									}
									
								}
							}	
							},
							dockedItems:[{
								xtype:'pagingtoolbar',
								width:'100%',
								displayInfo:false,
								align:'center',
								margin:'0 300 0 300',
								
								store:Ext.data.StoreManager.lookup('Movies'),
								items:[{
									
								xtype:'button',
								text:'Add',
								handler:function()
								{
									
								var myForm = Ext.create('Ext.window.Window',{
								    width: 500,
								    height: 550,
								    title: 'Add Film',
								    floating: true,
								    closable : true,
				
									defaults:
									{
										width:"90%",
										margin:'20 20 20 20',
										labelStyle:'color:#000000;text-style:bold;'
									},
									items:[
										{
											xtype:'textfield',
											id:'titleField',
											fieldLabel:'Title:',
											
										},
										
										{
											xtype:'textfield',
											fieldLabel:'Release year:',
											id:'releaseYearField'
										},
										
										{
											xtype:'textfield',
											fieldLabel:'Special Features:',
											id:'specialFeaturesField'
										},
										
										{
											xtype:'textfield',
											fieldLabel:'Rating:',
											id:'ratingField'
										},
										
										{
											xtype:'textfield',
											fieldLabel:'Language:',
											id:'languageField'
										},
										
										{
											xtype:'textfield',
											fieldLabel:'Director Name:',
											id:'directorField'
										},
										
										{
											xtype:'textfield',
											fieldLabel:'Description:',
											height:100,
											id:'descriptionField'
										}
										
									],
									
								});
								myForm.show();
								 var butons = 	Ext.create('Ext.Container',{
									
									itemAlign:'center',
									defaults:{
										margin:5,
										padding:10
									},
									items:[{
										xtype:'button',
										text:'Save',
										handler:function()
										{
											
										Ext.Ajax.request({
                       					url: 'http://localhost:8080/Summer_Internship_Backend/dummy.do',//Defined path of function defined in MVC 
                        				method: 'POST',       // controller
                        				params: {
		                            				title :Ext.getCmp('titleField').value,
													description :Ext.getCmp('descriptionField').value,
													releaseYear : Ext.getCmp('releaseYearField').value,
													language : Ext.getCmp('languageField').value,
													directorName : Ext.getCmp('directorField').value,
													rating : Ext.getCmp('ratingField').value,
													specialFeatures : Ext.getCmp('specialFeaturesField').value
                        						},
	                                   success: function () 
													{
	                            						Ext.Msg.alert("Data Added Succesfully");
	                       							},
                        				failure: function () 
													{
                            							alert('fail');
                       								 }
											
											
											
											
										});
										myForm.destroy();
										
										}},
									
									{
										xtype:'button',
										text:'Cancel'
									}]
								})
								
									myForm.add(butons);					
								}
							
								},
								{
									xtype:'button',
									text:'Edit',
									id:'editButton',
									disabled:true,
								handler:function()
								{
									
									var data = Ext.getCmp('detailsGrid').getSelectionModel();
									var r = data.getSelection();
									data = r[0];
									
									
								var myForm = new Ext.create('Ext.window.Window',{
								    width: 500,
								    height: 550,
								    title: 'Edit Film',
								    floating: true,
								    closable : true,
				
									defaults:
									{
										width:"90%",
										margin:'20 20 20 20',
										labelStyle:'color:#000000;text-style:bold;'
									},
									items:[
										{
											xtype:'textfield',
											fieldLabel:'Title:',
											id:'titleId',
											value:data.get('title')
											
										},
										
										{
											xtype:'textfield',
											fieldLabel:'Release year:',
											id:'releaseYearId',
											value:data.get('releaseYear')
										},
										
										{
											xtype:'textfield',
											fieldLabel:'Special Features:',
											id:'specialFeaturesId',
											value:data.get('specialFeatures')
										},
										
										{
											xtype:'textfield',
											fieldLabel:'Rating:',
											id:'ratingId',
											value:data.get('rating')
										},
										
										{
											xtype:'textfield',
											fieldLabel:'Language:',
											id:'languageId',
											value:data.get('language')
										},
										
										{
											xtype:'textfield',
											fieldLabel:'Director Name:',
											id:'directorId',
											value:data.get('directorName')
										},
										
										{
											xtype:'textfield',
											fieldLabel:'Description:',
											height:100,
											id:'descriptionId',
											value:data.get('description')
										}
										
									],
									
								});
								myForm.show();
								 var butons = 	Ext.create('Ext.Container',{
									
									itemAlign:'center',
									defaults:{
										margin:5,
										padding:10
									},
									items:[{
										xtype:'button',
										text:'Save',
								//IT IS FOR EDIT 
										handler:function()
										{
										
										
										Ext.Ajax.request({
                       					url: "http://localhost:8080/Summer_Internship_Backend/dummy.do?title="+Ext.getCmp('titleId').value+"&description="+Ext.getCmp('descriptionId').value+"&releaseYear="+Ext.getCmp('releaseYearId').value+"&language="+Ext.getCmp('languageId').value+"&directorName="+ Ext.getCmp('directorId').value+"&rating ="+Ext.getCmp('ratingId').value+"&specialFeatures ="+Ext.getCmp('specialFeaturesId').value+"&filmId="+data.get('filmId'),
                        				method: 'PUT',       // controller
                        				params: {
		                            				/*title :data.get('title'),
													description :data.get('description'),
													releaseYear : data.get('releaseyear'),
													language : data.get('language'),
													directorName : data.get('directorName'),
													rating : data.get('rating'),
													specialFeatures : data.get('specialFeatures'),
													filmId:data.get('filmId') */
												},
									
	                                   success: function () 
													{
														
	                            						Ext.Msg.alert("Data Updated Succesfully");
	                       							},
                        				failure: function () 
													{
                            							alert('fail');
                       								 }
											
											
											
											
										});
										myForm.destroy();
									}},
									{
										xtype:'button',
										text:'Cancel'
									}]
								})
								
									myForm.add(butons);					
								}
								},
								{
									xtype:'button',
									text:'Delete',
									id:'deleteButton',
									disabled:true,
									handler:function(){
										
										
										var win = Ext.create('Ext.window.Window',{
											title:'Delete Film',
											width:200,
											height:300,
											layout:'fit',
											items:[{
												xtype:'displayfield',
												fieldLabel:'Do you want to delete these items?',
												margin:'20 20 20 20',
												labelStyle:'color:#000000;text-style:bold;',
												padding:'10 10 10 10'
												
											}],
											
											bbar:[
												{
													xtype:'button',
													text:'OK',
													//IT IS FOR DELETE
													handler:function(){
														var data = Ext.getCmp('detailsGrid').getSelectionModel();
														var r = data.getSelection();
														data = r[0];
														
														var filmIds = new Array();
														for(var i = 0;i<r.length;i++)
														{
															filmIds.push(r[i].get('filmId'));
														}
														alert(filmIds);
														Ext.Ajax.request({
                       					url: 'http://localhost:8080/Summer_Internship_Backend/dummy.do?filmIds='+filmIds,//Defined path of function defined in MVC 
                        				method: 'DELETE',       // controller
                        				
	                                   success: function () 
													{
	                            						Ext.Msg.alert("Data Deleted Succesfully");
														win.destroy();
		                       							},
                        				failure: function () 
													{
                            							alert('fail');
                       								 }
											
											
											
											
										});
														
														//Delete functionality to be added to delet data from database.
													}
												},
												{
													xtype:'button',
													text:'Cancel',
													handler:function()
													{
														win.destroy();
													}
												}
											]
										}).show();
										
									}
								}]
							},
							]
							
							
						})
					}
		
		





});
