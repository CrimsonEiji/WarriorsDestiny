package com.example.WarriorsTest.services;

import com.example.WarriorsTest.enums.HeroClass;
import com.example.WarriorsTest.enums.SpellType;
import com.example.WarriorsTest.enums.UserRoles;
import com.example.WarriorsTest.models.entity.RoleEntity;
import com.example.WarriorsTest.models.entity.SpellEntity;
import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.repository.RoleRepository;
import com.example.WarriorsTest.repository.SpellsRepository;
import com.example.WarriorsTest.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final SpellsRepository spellsRepository;

    @Autowired
    public InitService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       SpellsRepository spellsRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.spellsRepository = spellsRepository;
    }

    @PostConstruct
    private void initBase() {
        initRoles();
        initUsers();
        initSpells();
    }

    private void initRoles() {
        if (roleRepository.count() == 0) {
            var adminRole = new RoleEntity().setUserRole(UserRoles.ADMIN);
            var moderatorRole = new RoleEntity().setUserRole(UserRoles.MODERATOR);
            roleRepository.save(adminRole);
            roleRepository.save(moderatorRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            var user1 = new UserEntity()
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setEmail("admin@admin.com")
                    .setRoles(roleRepository.findAll());

            userRepository.save(user1);
        }
    }

    private void initSpells() {
        List<SpellEntity> spells = new ArrayList<>();
        if (spellsRepository.count() == 0) {
            SpellEntity knightSpell1 = new SpellEntity();
            knightSpell1.setForClass(HeroClass.KNIGHT)
                    .setSpellType(SpellType.ATTACK)
                    .setName("Sweeping Edge")
                    .setStat(200)
                    .setManaConsumption(20);
            SpellEntity knightSpell2 = new SpellEntity();
            knightSpell2.setForClass(HeroClass.KNIGHT)
                    .setSpellType(SpellType.BUFF)
                    .setName("Guardian")
                    .setStat(50)
                    .setManaConsumption(20);
            SpellEntity knightSpell3 = new SpellEntity();
            knightSpell3.setForClass(HeroClass.KNIGHT)
                    .setSpellType(SpellType.ATTACK)
                    .setName("Vicious Strike")
                    .setStat(150)
                    .setManaConsumption(20);
            SpellEntity knightSpell4 = new SpellEntity();
            knightSpell4.setForClass(HeroClass.KNIGHT)
                    .setSpellType(SpellType.BUFF)
                    .setName("Battle cry")
                    .setStat(100)
                    .setManaConsumption(20);

            SpellEntity mageSpell1 = new SpellEntity();
            mageSpell1.setForClass(HeroClass.MAGE)
                    .setSpellType(SpellType.ATTACK)
                    .setName("Fire ball")
                    .setStat(250)
                    .setManaConsumption(20);
            SpellEntity mageSpell2 = new SpellEntity();
            mageSpell2.setForClass(HeroClass.MAGE)
                    .setSpellType(SpellType.BUFF)
                    .setName("Frost ball")
                    .setStat(250)
                    .setManaConsumption(20);
            SpellEntity mageSpell3 = new SpellEntity();
            mageSpell3.setForClass(HeroClass.MAGE)
                    .setSpellType(SpellType.ATTACK)
                    .setName("Lightning strike")
                    .setStat(360)
                    .setManaConsumption(20);
            SpellEntity mageSpell4 = new SpellEntity();
            mageSpell4.setForClass(HeroClass.MAGE)
                    .setSpellType(SpellType.BUFF)
                    .setName("Frost nova")
                    .setStat(250)
                    .setManaConsumption(20);

            SpellEntity hunterSpell1 = new SpellEntity();
            hunterSpell1.setForClass(HeroClass.HUNTER)
                    .setSpellType(SpellType.ATTACK)
                    .setName("Precision Shot")
                    .setStat(350)
                    .setManaConsumption(20);
            SpellEntity hunterSpell2 = new SpellEntity();
            hunterSpell2.setForClass(HeroClass.HUNTER)
                    .setSpellType(SpellType.BUFF)
                    .setName("Deadly Sweep")
                    .setStat(250)
                    .setManaConsumption(20);
            SpellEntity hunterSpell3 = new SpellEntity();
            hunterSpell3.setForClass(HeroClass.HUNTER)
                    .setSpellType(SpellType.ATTACK)
                    .setName("Lightning arrow")
                    .setStat(360)
                    .setManaConsumption(20);
            SpellEntity hunterSpell4 = new SpellEntity();
            hunterSpell4.setForClass(HeroClass.HUNTER)
                    .setSpellType(SpellType.BUFF)
                    .setName("Shadow arrow")
                    .setStat(100)
                    .setManaConsumption(20);

            spells.add(knightSpell1);
            spells.add(knightSpell2);
            spells.add(knightSpell3);
            spells.add(knightSpell4);
            spells.add(mageSpell1);
            spells.add(mageSpell2);
            spells.add(mageSpell3);
            spells.add(mageSpell4);
            spells.add(hunterSpell1);
            spells.add(hunterSpell2);
            spells.add(hunterSpell3);
            spells.add(hunterSpell4);

            spellsRepository.saveAllAndFlush(spells);

        }


    }
}
