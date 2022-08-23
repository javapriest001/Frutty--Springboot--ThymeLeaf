package com.example.frutty.Repository;

import com.example.frutty.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist , Integer> {

}
