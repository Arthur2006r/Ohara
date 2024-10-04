package br.com.ohara.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.Review;
import br.com.ohara.model.Usuario;
import br.com.ohara.service.ReviewService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<Review> inserir(@Valid @RequestBody Review review) {
		return ResponseEntity.ok(reviewService.inserir(review));
	}
	
	@GetMapping({"/{id}"})
    public ResponseEntity<Review> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.consultarPorId(id));
    }
	
	@GetMapping({"/", ""})
    public ResponseEntity<List<Review>> consultarTodos() {
        return ResponseEntity.ok(reviewService.consultarTodos());
    }
	
	@GetMapping({"/todos/{idManga}"})
    public ResponseEntity<List<Review>> consultarTodos(@PathVariable Long idManga) {
        return ResponseEntity.ok(reviewService.consultarTodosManga(idManga));
    }
	
	@GetMapping({"/seguidos/{idManga}/{idUsuario}"})
    public ResponseEntity<List<Review>> consultarSeguidos(@PathVariable Long idManga, @PathVariable Long idUsuario) {
        return ResponseEntity.ok(reviewService.consultarSeguidosManga(idManga, idUsuario));
    }
	
	@GetMapping({"/meus/{idManga}/{idUsuario}"})
    public ResponseEntity<List<Review>> consultarMeus(@PathVariable Long idManga, @PathVariable Long idUsuario) {
        return ResponseEntity.ok(reviewService.consultarMeusManga(idManga, idUsuario));
    }

	@DeleteMapping({ "/{idReview}" })
	public ResponseEntity<Review> excluir(@PathVariable Long idReview) {
		return ResponseEntity.ok(reviewService.excluir(idReview));
	}
}
