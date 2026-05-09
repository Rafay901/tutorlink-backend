package com.example.demo.controller;

import com.example.demo.model.Requirement;
import com.example.demo.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requirements")
@CrossOrigin(origins = "*")
public class RequirementController {

    @Autowired
    private RequirementRepository requirementRepository;

    // GET all requirements (teacher map — all open)
    @GetMapping
    public List<Requirement> getAllRequirements() {
        return requirementRepository.findAll();
    }

    // GET requirements by student id (My Requirements page)
    @GetMapping("/student/{studentId}")
    public List<Requirement> getByStudent(@PathVariable Long studentId) {
        return requirementRepository.findByStudentId(studentId);
    }

    // GET by status (open / contacted)
    @GetMapping("/status/{status}")
    public List<Requirement> getByStatus(@PathVariable String status) {
        return requirementRepository.findByStatus(status);
    }

    // POST — student posts a new requirement
    @PostMapping
    public ResponseEntity<?> createRequirement(@RequestBody Requirement requirement) {
        try {
            requirement.setStatus("open");
            return ResponseEntity.ok(requirementRepository.save(requirement));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving requirement: " + e.getMessage());
        }
    }

    // PATCH — update status (e.g. open → contacted)
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody java.util.Map<String, String> body) {
        Optional<Requirement> opt = requirementRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        Requirement req = opt.get();
        req.setStatus(body.get("status"));
        return ResponseEntity.ok(requirementRepository.save(req));
    }

    // DELETE — admin or student deletes a requirement
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequirement(@PathVariable Long id) {
        if (!requirementRepository.existsById(id)) return ResponseEntity.notFound().build();
        requirementRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
