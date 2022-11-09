package io.github.vinicius1001.catalog.controller;


import io.github.vinicius1001.catalog.dto.ClientDTO;
import io.github.vinicius1001.catalog.repository.ClientRepository;
import io.github.vinicius1001.catalog.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService  clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClientDTO> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                   @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                   @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return clientService.findAllPaged(pageRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ClientDTO> findById(@PathVariable Long id){
        return clientService.findById(id);

    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO save (@RequestBody ClientDTO clientDTO){
        return  clientService.save(clientDTO);

    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        clientService.update(id,clientDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        clientService.delete(id);
    }



}
