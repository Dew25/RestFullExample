/**
 * Как работает это приложение (демонстрационное)
 * 
 * Для сущности Book создан сервис -> BookFacadeREST,
 * который содержит методы работы с книгой.
 * Чтобы включить возможность работы с EJB BookFacadeREST
 * создан конфигурационный файл ApplicationConfig с анотацией @AplicationConfig,
 * с параметром "webapi". Это может быть произвольная строка. Она указывает шаблон
 * запроса для использования мисросервисов REST.
 * Клиент должен использовать запрос с указанием этого патерна.
 * Например: http://localhost:8080/RestFullExample/webapi/book/
 * Такой запрос скажет вэб контейнеру сервера приложений, что надо активировать
 * соответствующие инструменты и какой бин должен получить 
 * запрос от клиента. В данном случае слово "book" в запросе соответствует аннотации
 * @Path("book") в BookFacadeREST
 * Если клиент пошлет запрос GET, то сработает метод с аннотацией @GET у которого 
 * отсутствует уточнение запроса.
 * Можно вызывать конкретные методы, которые имеют свои паттерны, например:
 * http://localhost:8080/RestFullExample/webapi/book/count
 * Это заставить выполниться метод с аннотацией @Path("count") и клиенту будет 
 * отослано количество объектов этой сущности хранящихся в базе данных.
 * 
 * Таким образом появилась возможность создавать независимые сервисы с 
 * определенными алгоритмом обработки данных.
 * 
 * Чтобы на сервер могли обратиться клиенты развернутые в других доменах в приложение 
 * добавлен фильтр RestFullExampleCrossOriginResourceSharingFilter
 * 
 * Для использования аннотации @Inject добавлен файл beans.xml, где заменен параметр
 * по умолчанию "annotated", на "all".
 * 
 */
package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Melnikov
 */
@javax.ws.rs.ApplicationPath("webapi")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(filter.RestFullExampleCrossOriginResourceSharingFilter.class);
        resources.add(service.BookFacadeREST.class);
    }
    
}
