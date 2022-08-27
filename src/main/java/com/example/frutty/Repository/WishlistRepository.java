package com.example.frutty.Repository;

import com.example.frutty.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist , Integer> {
    @Query(value = "SELECT products.id , products.name, products.Description , products.price , products.category , products.image FROM wishlists INNER JOIN products ON wishlists.product_id = products.id WHERE customer_id = ?1" , nativeQuery = true)
    List<Wishlist> findWishlistByCustomerId(int id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM wishlists WHERE customer_id = ?1 AND product_id = ?2", nativeQuery = true)
    int deleteWishlistByUserIdAndProductId(int customer_id , int product_id);
}
