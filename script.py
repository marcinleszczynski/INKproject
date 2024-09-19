from newspaper import Article
import pandas as pd

data = []

with open('links.txt', 'r') as file:
    for line in file.readlines():
        try:
            url = line.strip()
            article = Article(url)
            article.download()
            article.parse()
            data.append([article.title, article.text, article.publish_date])
        except:
            ...

dataframe = pd.DataFrame(data)
dataframe.to_csv('local_news.csv', index=False)