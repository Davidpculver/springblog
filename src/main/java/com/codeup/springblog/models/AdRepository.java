package com.codeup.springblog.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//repository takes the place of our Dao's. gets the info from and writes to db
//the Ad is model (entity), Long is part of the magic; possibly represents the id
public interface AdRepository extends JpaRepository<Ad, Long> {
//    write out query almost the same as in mysql
    @Query("FROM Ad a WHERE a.id = ?1")
//    using this in AdController. Define that will perform certain actions; this will return an Ad
    Ad findById(long id);

//    use with wildcards. (term can be anything, as long as it matches the below method)
    @Query("FROM Ad a WHERE a.title like %:term%")

//    after entering find.. opens many options that we can use. Because pulling from the jparepository; magic
    Ad findByTitle(String term);


}
