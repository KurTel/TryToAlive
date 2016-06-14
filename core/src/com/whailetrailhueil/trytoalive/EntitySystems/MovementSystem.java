package com.whailetrailhueil.trytoalive.EntitySystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.whailetrailhueil.trytoalive.EntityComponents.MovementComponent;
import com.whailetrailhueil.trytoalive.EntityComponents.PositionComponent;

import java.util.Random;

/**
 * Created by User on 10.06.2016.
 */
public class MovementSystem extends IteratingSystem{
    private Random random = new Random();

    public MovementSystem() {
        //super(Family.getFamilyFor(PositionComponent.class, MovementComponent.class));
        super(Family.all(PositionComponent.class, MovementComponent.class).get());
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = entity.getComponent(PositionComponent.class);
        MovementComponent movement = entity.getComponent(MovementComponent.class);

        // для проверки работы системы зададим движение в случайном направлении
        if(random.nextInt(10) < 1){
            movement.getAcceleration().set(movement.getAccelerationValue(), 0).rotate(random.nextInt(360));
        }

        movement.getVelocity().add(movement.getAcceleration().cpy().scl(deltaTime));
        if(movement.getVelocity().len() > movement.getMaxVelocity()){
            movement.getVelocity().limit(movement.getMaxVelocity());
        }
        position.getPosition().add(movement.getVelocity().cpy().scl(deltaTime));
    }
}
