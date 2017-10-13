 $(document).ready(function(){
        var i = 0;
        $("#add_img").click(function(e){
            i++;
            $("#dynamic_img").prepend('<tr id=row'+i+'><td><input type="file" onchange="myfn(this)" name="images[]" id="images'+i
            +'" data-panelid="images'+i+'" class="form-control images_list" accept="image/gif, image/png, image/jpeg, image/pjpeg" style="display:none" />'
            +'<label for="images'+i+'" id="images'+i+'" class="w3-button w3-light-gray" style="padding:10px 10px;">Choose a photo</label></td><td id="img_preview_td"><img id="img_preview'+i+'" /></td><td><button name="remove" id="'+i
            +'" class="btn-remove">X</button></td></tr>');
            e.preventDefault();
        });

        $(document).on("click",".btn-remove",function(){
             var button_id = $(this).attr("id");
             $("#row"+button_id+"").remove();
        });
    });

      function myfn(myinput) {
            var name = $(myinput).attr("name");
            var id = $(myinput).attr("id");
            var val = $(myinput).val();
            debugger;
            switch(val.substring(val.lastIndexOf('.') + 1).toLowerCase()){
                case 'gif': case 'jpg': case 'png': case 'jpeg':
                    readURL(myinput);
                    break;
                default:
                    $(this).val('');
                    break;
            }
        }


        function readURL(myinput,btnid) {
           debugger;
            if (myinput.files && myinput.files[0]) {
                  var reader = new FileReader();
                reader.onload = function (e) {
                    $('#img_preview' + $(myinput).attr("id").replace('images','') ).attr('src', e.target.result).width(150).height(200);
                    $(myinput).attr("id").hide();
                }
                reader.readAsDataURL(myinput.files[0]);
            }
        }
        