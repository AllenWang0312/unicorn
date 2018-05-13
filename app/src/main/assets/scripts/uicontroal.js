 $(function() {

            var now = new Date();
            var $video = $("#videoplayer");
            var $music = $("#musicplayer");
            var $image = $("#imageplayer");

            var url = 'files' + '?' + now.getTime();
            // 请求JSON数据
            $.getJSON(url, function(data) {
                // 编辑JSON数组
                for (var i = 0; i < data.length; i++) {
                    // 为每个对象生成一个li标签，添加到页面的ul中
                   var  chars[] = data[i].path.split("/")
                   var len = chars.length;
                        var $li = $(
                          '<li class="file">'
                        +data[i].name
                        +'<a class="filedir"/>'
                        +chars[length-1]
                        +'<a>'
                        '</li>');
                        $li.attr("path", data[i].path);

                   if(data[i].type=="video"){
                        $("#videolist").append($li);
                        // 点击的时候，获取路径，设置给video的src属性
                         $li.click(function() {
                         $video.show();
                        $music.hide();
                        $image.hide();
                           var p = "/files/" + $(this).attr("path");
                        $video.attr("src", p);

                        $video[0].play();
                    });}
                    else if(data[i].type=="music"){
                        $("#musiclist").append($li);
                          $li.click(function() {
                             $video.hide();
                        $music.show();
                        $image.hide();
                         var p = "/files/" + $(this).attr("path");
                        $music.attr("src", p);

                        $music[0].play();
                    }); }else if(data[i].type=="image"){
                          $("#imagelist").append($li);
                        $li.click(function() {
                         var p = "/files/" + $(this).attr("path");
                        $image.attr("src", p);
                         $video.hide();
                        $music.hide();
                        $image.show();
                    });}
                }

                $("#expend_video").click(function(){
                if($("#expend_video").text()=="收起"){
                $("#expend_video").text("展开");
                  $("#videolist").hide();
                }else{
                  $("#expend_video").text("收起");
                  $("#videolist").show();
                }
                });

                  $("#expend_music").click(function(){
                if($("#expend_music").text()=="收起"){
                $("#expend_music").text("展开");
                  $("#musiclist").hide();
                }else{
                  $("#expend_music").text("收起");
                  $("#musiclist").show();
                }
                });

                  $("#expend_image").click(function(){
                if($("#expend_image").text()=="收起"){
                $("#expend_image").text("展开");
                  $("#imagelist").hide();
                }else{
                  $("#expend_image").text("收起");
                  $("#imagelist").show();
                }
                });
            });
        });
