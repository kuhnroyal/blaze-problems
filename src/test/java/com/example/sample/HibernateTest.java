
/*
 * Copyright 2014 - 2023 Blazebit.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sample;

import com.example.model.Cat;
import com.example.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class HibernateTest {

    @Test
    public void sampleTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = emf.createEntityManager();

        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        EntityType<?> cat = entities.stream().filter(e -> e.getJavaType().equals(Cat.class)).findFirst().get();
        EntityType<?> person = entities.stream().filter(e -> e.getJavaType().equals(Person.class)).findFirst().get();
        Assert.assertNotNull(cat);
        Assert.assertNotNull(person);

        Assert.assertTrue(cat.hasVersionAttribute());
        Assert.assertTrue(person.hasVersionAttribute());

        Assert.assertTrue(cat.getSingularAttributes().stream().anyMatch(SingularAttribute::isVersion));
        Assert.assertTrue(person.getSingularAttributes().stream().anyMatch(SingularAttribute::isVersion));
    }
}
