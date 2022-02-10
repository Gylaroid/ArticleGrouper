//import java.util.ArrayList;

public class ArticleGrouper {
    public static void main(String[] args){
        int availableTime = 100;
//        Не реализованно!
//        ArrayList<Article> allArticlesInArrayList = new ArrayList<Article>();
//        allArticlesInArrayList.add(new Article("Борисов", 50));
//        allArticlesInArrayList.add(new Article("Барух", 20));
//        allArticlesInArrayList.add(new Article("Мартин", 30));
//        allArticlesInArrayList.add(new Article("Ганичев", 10));


//        Создаем массив статей и заполняем его
        Article[] allArticles = new Article[4];
        allArticles[0] = new Article("Борисов", 50);
        allArticles[1] = new Article("Барух", 20);
        allArticles[2] = new Article("Мартин", 30);
        allArticles[3] = new Article("Ганичев", 10);

//        Вызываем волшебную функцию, получаем список для чтения
        Article[] readingList = groupArticles(allArticles, availableTime);

//        Распечаетываем список для чтения
        for(int i = 0; i < readingList.length; i++){
            printArtcile(readingList[i]);
        }
    }

//    Не реализованно!!
//    private void groupingArticlesFromArrayList(int availibleTime, ArrayList allArticles){
//
//    }

//    Группировка статей
    public static Article[] groupArticles(Article[] allArticles, int availableTime){
        Article[] readingList = new Article[0];
        int biggestArticleIndex = 0;

        while(true){
//            Получаем индекс статьи с наибольшим временм прочтения
            biggestArticleIndex = biggestArticle(allArticles);
//            Если время ее прочтения укладывается в доступное
            if(allArticles[biggestArticleIndex].readingTime <= availableTime){
//                Уменьшаем доступное время
                availableTime = availableTime - allArticles[biggestArticleIndex].readingTime;
//                Добавляем статью в список для чтения
                readingList = addToReadingList(readingList, allArticles[biggestArticleIndex]);
//                Удаляем уже добавленную стаью из изначального списка
                allArticles = removeExtra(allArticles, biggestArticleIndex);
            }
//            Иначе прерываем выполнение цикла, так как оставшиеся
//            статьи невозможно уложить в доступное время
            else {
                break;
            }
        }

        return readingList;
    }

    //    Вывод списка статей в консоль
    private static void printArtcile(Article article){
        System.out.println("Author: " + article.authorSurname + " " + article.readingTime + " мин");
    }

//    Находит статью с наибольшим временем прочтения
    private static int biggestArticle(Article[] allArticles){
        Article biggestArticle = allArticles[0];
        int biggestArticleIndex = 0;
        for(int i = 1; i < allArticles.length; i++){
            if(biggestArticle.readingTime < allArticles[i].readingTime){
                biggestArticle = allArticles[i];
                biggestArticleIndex = i;
            }
        }
        return biggestArticleIndex;
    }

    // Выкидывает из изначального массива статей указанную
    // в extraArticleIndex и возрващает уменьшенный массив
    private static Article[] removeExtra(Article[] allArticles, int extraArticleIndex){
        Article[] tmpArticles = new Article[allArticles.length - 1];
        int offset = 0;
        for (int i = 0; i < tmpArticles.length; i++) {
            if (i == extraArticleIndex) {
                offset = 1;
            }
            tmpArticles[i] = allArticles[i + offset];
        }
        return tmpArticles;
    }

//    Формирует больший массив, добавляя туда статью
    private static Article[] addToReadingList(Article[] readingList, Article article){
        Article[] tmpArticle = new Article[readingList.length + 1];
        for(int i = 0; i < readingList.length; i++){
            tmpArticle[i] = readingList[i];
        }
        tmpArticle[tmpArticle.length - 1] = article;
        return  tmpArticle;
    }
}

class Article{
    String authorSurname;
    int readingTime;

    Article(String author, int readingTime){
        this.authorSurname = author;
        this.readingTime = readingTime;
    }
}
