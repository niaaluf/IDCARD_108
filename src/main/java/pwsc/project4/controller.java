/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwsc.project4;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author asus
 */
@Controller
public class controller {
    
  
    @RequestMapping ("/getData")
    public String getData(@RequestParam("nama")String text,
                          @RequestParam("tanggal")@DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                          @RequestParam("image")MultipartFile file, Model model)
                          throws IOException, ParseException {
        
        String blob = Base64.encodeBase64String(file.getBytes());
        String gambar = "data:image/jpeg;base64,".concat(blob);
        
        SimpleDateFormat tanggal = new SimpleDateFormat("EE/dd/MMMM/yyyy");
        String newTanggal = tanggal.format(date);
       
        model.addAttribute("name", text);
        model.addAttribute("tgl", newTanggal);
        model.addAttribute("gambar", gambar);
        
        return "tampilan";
    }
}
