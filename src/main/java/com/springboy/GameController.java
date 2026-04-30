package com.springboy;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GameController {

    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> ping() {
        return Map.of("status", "ok");
    }

    @GetMapping(value = "/game", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> game(@RequestParam(required = false, defaultValue = "abc123") String session) throws IOException {
        // Serve static PNG from resources
        Resource resource = new ClassPathResource("static/game.png");
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
        
        // Explicitly set HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    // === Dynamic PNG generation (commented out - requires headless graphics) ===
    // @GetMapping(value = "/game", produces = MediaType.IMAGE_PNG_VALUE)
    // public ResponseEntity<byte[]> gameDynamic(@RequestParam(required = false, defaultValue = "abc123") String session) throws IOException {
    //     BufferedImage image = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
    //     Graphics2D g = image.createGraphics();
    //     g.setColor(Color.BLACK);
    //     g.fillRect(0, 0, 128, 128);
    //     g.setColor(Color.WHITE);
    //     g.setFont(new Font("Monospaced", Font.BOLD, 20));
    //     g.drawString("HELLO", 30, 64);
    //     g.dispose();
    //     ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //     ImageIO.write(image, "png", baos);
    //     byte[] imageBytes = baos.toByteArray();
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.IMAGE_PNG);
    //     return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    // }
}