import logo from './logo.svg'
import './App.css'
import { useState, useEffect } from 'react'
import Select from 'react-select'

function App() {
	
	const [state, setState] = useState('')
	const [stateOptions, setStateOptions] = useState([])
	const [city, setCity] = useState('')
	const [cityOptions, setCityOptions] = useState([])
	const [articles, setArticles] = useState([])
	const [selected, setSelected] = useState('')
	const [articleId, setArticleId] = useState(0)
	const [currentArticle, setCurrentArticle] = useState(null)
	
	useEffect(() => {
		const fetchData = async () => {
			const response = await fetch("http://localhost:8080/api/city/allStates")
			const states = await response.json()
			const opts = states.map(state => ({
				value: state,
				label: state
			}))
			setStateOptions(opts)
		}
		fetchData()
	}, [])
	
	useEffect(() => {
		const fetchData = async () => {
			const response = await fetch(`http://localhost:8080/api/city?state=${state}`)
			const cities = await response.json()
			const opts = cities.map(city => ({
				value: `${city.name}, ${city.state}`,
				label: `${city.name}, ${city.state}`
			}))
			setArticleId(0)
			setCityOptions(opts)
		}
		fetchData()
	}, [state])
	
	useEffect(() => {
		const fetchData = async () => {
			const [cityName, cityState] = city.split(", ")
			const response = await fetch(`http://localhost:8080/api/news/byCity?name=${cityName}&state=${cityState}`)
			const news = await response.json()
			setArticleId(0)
			setArticles(news)
		}
		if(city !== '') {
			fetchData()
		}
	}, [city])
	
	const loadArticle = (id) => {
		const fetchData = async () => {
			const response = await fetch(`http://localhost:8080/api/news?id=${id}`)
			const article = await response.json()
			setCurrentArticle(article)
			setArticleId(article.id)
		}
		fetchData()
	}
	
	return (
		<div className="App">
			<Select
				options={stateOptions}
				onChange={(selectedOption) => {
					setState(selectedOption.value)
				}}
				placeholder="Select state"
			/>
			<Select
				options={cityOptions}
				onChange={(selectedOption) => {
					setCity(selectedOption.value)
				}}
				placeholder="Select your city"
			/>
			{
				articleId == 0 ? (
					articles.map((article) => (
						<div className="chooseArticle"
							key={article.id} 
							onClick={() => loadArticle(article.id)}
							style={{ width: "60%", marginLeft: "20%", cursor: 'pointer', padding: "0px" }} >
							<p>{article.title}</p>
						</div>
					))
				) : (
					<div style={{width: "60%", marginLeft: "20%"}}>
						<p><button onClick={() => setArticleId(0)} style={{background: "red"}}>X</button><h1><b>{currentArticle.title}</b></h1></p>
						<hr/>
						<p>{currentArticle.text}</p>
					</div>
				)
			}
		</div>
	)
}

export default App
