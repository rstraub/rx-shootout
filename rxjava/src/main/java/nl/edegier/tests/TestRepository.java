package nl.edegier.tests;

import java.util.List;

/**
 * Created by Erwin on 09/06/2017.
 */
public class TestRepository {

    public void getOldestWaitingTest(JsonObject query, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.findWithOptions(COLLECTION_TESTS, query, new FindOptions().setSort(new JsonObject().put("lastChanged", -1)), result -> {
            List<Test> tests = result.result();
            for(Test test : tests){
                this.environmentService.environmentIsLocked(test.getEnvironment(), envLockedResult -> { // async call to db
                    if (envLockedResult.result()) {
                        continue;
                    } else {
                        if (test.isRunIsolated()) {
                            getRunningTestsForEnvironment(test.getEnvironment(), result -> {   // async call to db
                                if (result.result().isEmpty()) {
                                    resultHandler.handle(Future.succeededFuture(test.toJson()));
                                    break;
                                } else {
                                    continue;
                                }
                            });
                        } else {
                            resultHandler.handle(Future.succeededFuture(test.toJson()));
                            break;
                        }
                    }

                });
            }
        });
    }

//
//    private void checkRunIsolated(List<JsonObject> tests, Test test, Handler<AsyncResult<JsonObject>> resultHandler) {
//
//        if (test.isRunIsolated()) {
//            getRunningTestsForEnvironment(test.getEnvironment(), result -> {
//
//                if (result.result().isEmpty()) {
//                    resultHandler.handle(Future.succeededFuture(test.toJson()));
//                } else {
//                    tests.remove(0);
//                    returnEligibleTest(tests, resultHandler);
//                }
//            });
//        }
//    }
//
//    private void getRunningTestsForEnvironment(String environment, Handler<AsyncResult<List<JsonObject>>> resultHandler) {
//        JsonObject query = new JsonObject();
//        query.put(Test.KEY_STATUS, Status.RUNNING);
//        query.put("config.Environment", environment);
//        mongo.findWithOptions(COLLECTION_TESTS, query, new FindOptions().setSort(new JsonObject().put("lastChanged", -1)), result -> {
//                resultHandler.handle(Future.succeededFuture(result.result()));
//
//
//        });
//    }
}
