(ns oo-patterns.dependency-injection)

;; there is no direct analog to dependency injection in clojure.  We
;; pass functions

(defn get-movie
  [movie-id]
  {:id "42" :title "A Movie"})

(defn get-favorite-videos
  [user-id]
  [{:id "1"}])

;; we pass the above functions into the following
(defn get-favorite-decorated-videos
  [user-id get-movie get-favorite-videos]
  (for [video (get-favorite-videos user-id)]
    {:movie (get-movie (:id video))
     :video video}))

;; usage
; (get-favorite-decorated-videos "1" get-movie get-favorite-videos)


;; how to stub out dependencies when they are not passed in
(defn get-favorite-decorated-videos-2
  [user-id]
  (for [video (get-favorite-videos user-id)]
    {:movie (get-movie (:id video))
     :video video}))

;; usage
; (get-favorite-decorated-videos-2 "1")

;; to stub out dependencies
(defn test-get-movie
  [movie-id]
  {:id "43" :title "A Movie-2"})

(defn test-get-favorite-videos
  [user-id]
  [{:id "2"}])

;; usage
(comment
(with-redefs
  [get-favorite-videos test-get-favorite-videos
   get-movie test-get-movie]
  (doall (get-favorite-decorated-videos-2 "2")))
)
