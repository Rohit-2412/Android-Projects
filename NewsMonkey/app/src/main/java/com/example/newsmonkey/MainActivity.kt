import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.jacksonandroidnetworking.JacksonParserFactory
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    // declaring the views
    private var mProgressBar: ProgressBar? = null
    private var mRecyclerView: RecyclerView? = null

    // declaring an ArrayList of articles
    private var mArticleList: ArrayList<NewsArticle>? = null
    private var mArticleAdapter: ArticleAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing the Fast Android Networking Library
        AndroidNetworking.initialize(applicationContext)

        // setting the JacksonParserFactory
        AndroidNetworking.setParserFactory(JacksonParserFactory())

        // assigning views to their ids
        mProgressBar = findViewById<View>(R.id.progressbar_id) as ProgressBar
        mRecyclerView = findViewById<View>(R.id.recyclerview_id) as RecyclerView

        // setting the recyclerview layout manager
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)

        // initializing the ArrayList of articles
        mArticleList = ArrayList<NewsArticle>()

        // calling get_news_from_api()
        get_news_from_api()
    }

    fun get_news_from_api() {
        // clearing the articles list before adding news ones
        mArticleList!!.clear()

        // Making a GET Request using Fast
        // Android Networking Library
        // the request returns a JSONObject containing
        // news articles from the news api
        // or it will return an error
        AndroidNetworking.get("https://newsapi.org/v2/top-headlines")
            .addQueryParameter("country", "in")
            .addQueryParameter("apiKey", API_KEY)
            .addHeaders("token", "1234")
            .setTag("test")
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener() {
                fun onResponse(response: JSONObject) {
                    // disabling the progress bar
                    mProgressBar!!.visibility = View.GONE

                    // handling the response
                    try {

                        // storing the response in a JSONArray
                        val articles = response.getJSONArray("articles")

                        // looping through all the articles
                        // to access them individually
                        for (j in 0 until articles.length()) {
                            // accessing each article object in the JSONArray
                            val article = articles.getJSONObject(j)

                            // initializing an empty ArticleModel
                            val currentArticle = NewsArticle()

                            // storing values of the article object properties
                            val author = article.getString("author")
                            val title = article.getString("title")
                            val description = article.getString("description")
                            val url = article.getString("url")
                            val urlToImage = article.getString("urlToImage")
                            val publishedAt = article.getString("publishedAt")
                            val content = article.getString("content")

                            // setting the values of the ArticleModel
                            // using the set methods
                            currentArticle.setAuthor(author)
                            currentArticle.setTitle(title)
                            currentArticle.setDescription(description)
                            currentArticle.setUrl(url)
                            currentArticle.setUrlToImage(urlToImage)
                            currentArticle.setPublishedAt(publishedAt)
                            currentArticle.setContent(content)

                            // adding an article to the articles List
                            mArticleList!!.add(currentArticle)
                        }

                        // setting the adapter
                        mArticleAdapter = ArticleAdapter(applicationContext, mArticleList)
                        mRecyclerView!!.adapter = mArticleAdapter
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        // logging the JSONException LogCat
                        Log.d(TAG, "Error : " + e.message)
                    }
                }

                fun onError(error: ANError) {
                    // logging the error detail and response to LogCat
                    Log.d(TAG, "Error detail : " + error.getErrorDetail())
                    Log.d(TAG, "Error response : " + error.getResponse())
                }
            })
    }

    companion object {
        // TODO : set the API_KEY variable to your api key
        private const val API_KEY = ""

        // setting the TAG for debugging purposes
        private const val TAG = "MainActivity"
    }
}