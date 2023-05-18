   function formCheck(frm) {
        let msg ='';

        if(frm.id.value.length < 3) {
            setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
            return false;
        }

        if(frm.pwd.value.length < 3) {
            setMessage('pwd의 길이는 3이상이어야 합니다.', frm.pwd);
            return false;
        }           
       
       return true;
   }

   function setMessage(msg, element){
        document.getElementById("msg").innerHTML = msg;

        if(element) {
            element.select();
        }
   }