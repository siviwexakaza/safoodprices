
const fiziks = {

    /**
   * Find a related item by id for accessTokens.
   *
   * @param {Number} mass mass of the object in kg
   *
   * @param {Number} acceleration acceleration of the object in m.s^2
   *
   * @returns {Number} The force produced based on the mass and accelaration of the object.
   *
   * <em>
   * (The remote method definition does not provide any description.
   * This usually means the response is a `Admin` object.)
   * </em>
   */
    forceFromMassAcceleration: function(mass, acceleration){

        return mass * acceleration

    },

    /**
   * Find a related item by id for accessTokens.
   *
   * @param {Number} force force of in N
   *
   * @param {Number} accelaration accelaration of the object in m.s^2
   *
   * @returns {Number} The force produced based on the mass and accelaration of the object.
   *
   * <em>
   * (The remote method definition does not provide any description.
   * This usually means the response is a `Admin` object.)
   * </em>
   */

    massFromAccelerationForce: function(force, accelaration){
        return force / accelaration
    }
}

module.exports = fiziks