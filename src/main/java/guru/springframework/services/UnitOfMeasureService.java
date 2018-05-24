package guru.springframework.services;

import java.util.Set;

import guru.springframework.commands.UnitOfMeasureCommand;

/**
 * Created by piyush.b.kumar on May 24, 2018.
 */
public interface UnitOfMeasureService {

	Set<UnitOfMeasureCommand> listAllUoms();

}
